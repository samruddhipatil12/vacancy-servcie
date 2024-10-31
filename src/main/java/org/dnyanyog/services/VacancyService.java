package org.dnyanyog.services;

import java.util.List;
import org.dnyanyog.dto.VacancyRequest;
import org.dnyanyog.dto.VacancyResponse;

public interface VacancyService {
  VacancyResponse addVacancy(VacancyRequest request) throws Exception;

  VacancyResponse updateVacancy(Long vacancyId, VacancyRequest request);

  VacancyResponse getSingleVacancy(String tenant, Long vacancyId);

  VacancyResponse deleteVacancy(Long vacancyId);

  List<VacancyResponse> getVacanciesByTenant(String tenant);
}
