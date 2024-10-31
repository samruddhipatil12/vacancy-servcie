package org.dnyanyog.controller;

import java.util.List;
import org.dnyanyog.dto.VacancyRequest;
import org.dnyanyog.dto.VacancyResponse;
import org.dnyanyog.services.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vacancy")
public class VacancyServiceController {

  @Autowired private VacancyService vacancyService;

  @PostMapping(
      path = "/add",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public VacancyResponse addVacancy(@RequestBody VacancyRequest vacancyRequest) throws Exception {
    return vacancyService.addVacancy(vacancyRequest);
  }

  @PutMapping(
      path = "/{vacancy_id}",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public VacancyResponse updateVacancy(
      @PathVariable("vacancy_id") Long vacancyId, @RequestBody VacancyRequest vacancyRequest) {
    return vacancyService.updateVacancy(vacancyId, vacancyRequest);
  }

  @GetMapping(
      path = "/tenant/{tenant}/vacancy/{vacancy_id}",
      produces = {"application/json", "application/xml"})
  public VacancyResponse getVacancy(
      @PathVariable("tenant") String tenant, @PathVariable("vacancy_id") Long vacancyId) {
    return vacancyService.getSingleVacancy(tenant, vacancyId);
  }

  @GetMapping(
      path = "/tenant/{tenant}",
      produces = {"application/json", "application/xml"})
  public List<VacancyResponse> getVacanciesByTenant(@PathVariable("tenant") String tenant) {
    return vacancyService.getVacanciesByTenant(tenant);
  }

  @DeleteMapping(
      path = "/{vacancy_id}",
      produces = {"application/json", "application/xml"})
  public VacancyResponse deleteVacancy(@PathVariable("vacancy_id") Long vacancyId) {
    return vacancyService.deleteVacancy(vacancyId);
  }
}
