package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.MissionListResponseDTO;

public interface MissionQueryService {

    MissionListResponseDTO getMissionListByStore(Long storeId, Integer page);
}