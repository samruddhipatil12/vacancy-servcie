package org.dnyanyog.services;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.VacancyRequest;
import org.dnyanyog.dto.VacancyResponse;
import org.dnyanyog.entity.Vacancy;
import org.dnyanyog.repo.VacancyServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacancyServiceImp implements VacancyService {

  @Autowired private VacancyServiceRepository vacancyRepo;

  @Override
  public VacancyResponse addVacancy(@Valid VacancyRequest request) throws Exception {
    VacancyResponse vacancyResponse = new VacancyResponse();

    if (vacancyRepo
        .findByVacancyNameAndTenant(request.getVacancyName(), request.getTenant())
        .isEmpty()) {
      Vacancy newVacancy =
          new Vacancy(
              request.getVacancyName(),
              request.getJobTitle(),
              request.getDescription(),
              request.getHiringManager(),
              request.getNumberOfPosition(),
              request.getTenant());

      vacancyRepo.save(newVacancy);
      populateVacancyResponse(vacancyResponse, newVacancy);
      vacancyResponse.setStatus(ResponseCode.VACANCY_ADDED.getStatus());
      vacancyResponse.setMessage(ResponseCode.VACANCY_ADDED.getMessage());
    } else {
      vacancyResponse.setStatus(ResponseCode.VACANCY_ALREADY_EXISTS.getStatus());
      vacancyResponse.setMessage(ResponseCode.VACANCY_ALREADY_EXISTS.getMessage());
    }

    return vacancyResponse;
  }

  @Override
  public VacancyResponse updateVacancy(Long vacancyId, VacancyRequest request) {
    VacancyResponse vacancyResponse = new VacancyResponse();

    return vacancyRepo
        .findById(vacancyId)
        .map(
            vacancy -> {
              updateVacancyFields(vacancy, request);
              vacancyRepo.save(vacancy);
              populateVacancyResponse(vacancyResponse, vacancy);
              vacancyResponse.setStatus(ResponseCode.VACANCY_UPDATED.getStatus());
              vacancyResponse.setMessage(ResponseCode.VACANCY_UPDATED.getMessage());
              return vacancyResponse;
            })
        .orElseGet(
            () -> {
              vacancyResponse.setStatus(ResponseCode.VACANCY_NOT_FOUND.getStatus());
              vacancyResponse.setMessage(ResponseCode.VACANCY_NOT_FOUND.getMessage());
              return vacancyResponse;
            });
  }

  @Override
  public VacancyResponse getSingleVacancy(String tenant, Long vacancyId) {
    VacancyResponse vacancyResponse = new VacancyResponse();

    return vacancyRepo
        .findByVacancyIdAndTenant(vacancyId, tenant)
        .map(
            vacancy -> {
              populateVacancyResponse(vacancyResponse, vacancy);
              vacancyResponse.setStatus(ResponseCode.VACANCY_RETRIEVED.getStatus());
              vacancyResponse.setMessage(ResponseCode.VACANCY_RETRIEVED.getMessage());
              return vacancyResponse;
            })
        .orElseGet(
            () -> {
              vacancyResponse.setStatus(ResponseCode.VACANCY_NOT_FOUND.getStatus());
              vacancyResponse.setMessage(
                  ResponseCode.VACANCY_NOT_FOUND.getMessage() + " for this tenant");
              return vacancyResponse;
            });
  }

  @Override
  public VacancyResponse deleteVacancy(Long vacancyId) {
    VacancyResponse vacancyResponse = new VacancyResponse();

    return vacancyRepo
        .findById(vacancyId)
        .map(
            vacancy -> {
              vacancy.setStatus(Vacancy.Status.CANCELLED);
              vacancyRepo.save(vacancy);
              populateVacancyResponse(vacancyResponse, vacancy);
              vacancyResponse.setStatus(ResponseCode.VACANCY_CANCELLED.getStatus());
              vacancyResponse.setMessage(ResponseCode.VACANCY_CANCELLED.getMessage());
              return vacancyResponse;
            })
        .orElseGet(
            () -> {
              vacancyResponse.setStatus(ResponseCode.VACANCY_NOT_FOUND.getStatus());
              vacancyResponse.setMessage(ResponseCode.VACANCY_NOT_FOUND.getMessage());
              return vacancyResponse;
            });
  }

  @Override
  public List<VacancyResponse> getVacanciesByTenant(String tenant) {
    return vacancyRepo.findByTenant(tenant).stream()
        .map(
            vacancy -> {
              VacancyResponse response = new VacancyResponse();
              populateVacancyResponse(response, vacancy);
              return response;
            })
        .collect(Collectors.toList());
  }

  private void updateVacancyFields(Vacancy vacancy, VacancyRequest request) {
    vacancy.setVacancyName(request.getVacancyName());
    vacancy.setJobTitle(request.getJobTitle());
    vacancy.setDescription(request.getDescription());
    vacancy.setHiringManager(request.getHiringManager());
    vacancy.setNumberOfPositions(request.getNumberOfPosition());
    vacancy.setTenant(request.getTenant());

    try {
      vacancy.setStatus(Vacancy.Status.valueOf(request.getStatus().toUpperCase()));
    } catch (IllegalArgumentException e) {
      throw new RuntimeException("Invalid status: " + request.getStatus());
    }
  }

  private void populateVacancyResponse(VacancyResponse response, Vacancy vacancy) {
    response.setVacancyId(vacancy.getVacancyId());
    response.setVacancyName(vacancy.getVacancyName());
    response.setJobTitle(vacancy.getJobTitle());
    response.setDescription(vacancy.getDescription());
    response.setHiringManager(vacancy.getHiringManager());
    response.setNumberOfPosition(vacancy.getNumberOfPositions());
    response.setStatus(vacancy.getStatus().name());
    response.setTenant(vacancy.getTenant());
  }
}
