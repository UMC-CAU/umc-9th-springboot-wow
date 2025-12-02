package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.MissionDetailResponseDTO;
import com.example.umc9th.domain.mission.dto.MissionListResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.enums.Status;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.member.repository.MemberMissionRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;

import com.example.umc9th.global.apiPayload.exception.GeneralException;
import com.example.umc9th.global.apiPayload.exception.ExceptionCode;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;

    private static final int PAGE_SIZE = 10;

    @Override
    public MissionListResponseDTO getMissionListByStore(Long storeId, Integer page) {

        if (!storeRepository.existsById(storeId)) {
            throw new GeneralException(ExceptionCode.STORE_NOT_FOUND);
        }

        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);

        Page<Mission> missionPage = missionRepository.findAllByStoreId(storeId, pageable);

        List<MissionDetailResponseDTO> missionDTOs = missionPage.getContent().stream()
                .map(MissionDetailResponseDTO::toDTO)
                .collect(Collectors.toList());

        return MissionListResponseDTO.builder()
                .missionList(missionDTOs)
                .listSize(missionPage.getContent().size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }

    @Override
    public MissionListResponseDTO getOngoingMissionList(Long memberId, Integer page) {

        if (!memberRepository.existsById(memberId)) {
            throw new GeneralException(ExceptionCode.MEMBER_NOT_FOUND);
        }

        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);

        Page<MemberMission> ongoingMissionPage = memberMissionRepository.findAllByMemberIdAndStatus(
                memberId,
                Status.ONGOING,
                pageable
        );

        List<MissionDetailResponseDTO> missionDTOs = ongoingMissionPage.getContent().stream()
                .map(MemberMission::getMission)
                .map(MissionDetailResponseDTO::toDTO)
                .collect(Collectors.toList());


        return MissionListResponseDTO.builder()
                .missionList(missionDTOs)
                .listSize(ongoingMissionPage.getContent().size())
                .totalPage(ongoingMissionPage.getTotalPages())
                .totalElements(ongoingMissionPage.getTotalElements())
                .isFirst(ongoingMissionPage.isFirst())
                .isLast(ongoingMissionPage.isLast())
                .build();
    }
}