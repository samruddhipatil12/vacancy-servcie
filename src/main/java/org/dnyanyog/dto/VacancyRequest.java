package org.dnyanyog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VacancyRequest {

  @NotBlank(message = "Vacancy name is required")
  private String vacancyName;

  @NotBlank(message = "Job title is required")
  private String jobTitle;

  private String description;
  private String hiringManager;

  @NotNull(message = "Number of positions is required")
  private int numberOfPosition;

  private String costCenter;
  private String vendorName;

  @NotBlank(message = "Tenant is required")
  private String tenant;

  @NotBlank(message = "Status is required")
  private String status;

  public String getVacancyName() {
    return vacancyName;
  }

  public void setVacancyName(String vacancyName) {
    this.vacancyName = vacancyName;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getHiringManager() {
    return hiringManager;
  }

  public void setHiringManager(String hiringManager) {
    this.hiringManager = hiringManager;
  }

  public int getNumberOfPosition() {
    return numberOfPosition;
  }

  public void setNumberOfPosition(int numberOfPosition) {
    this.numberOfPosition = numberOfPosition;
  }

  public String getCostCenter() {
    return costCenter;
  }

  public void setCostCenter(String costCenter) {
    this.costCenter = costCenter;
  }

  public String getVendorName() {
    return vendorName;
  }

  public void setVendorName(String vendorName) {
    this.vendorName = vendorName;
  }

  public String getTenant() {
    return tenant;
  }

  public void setTenant(String tenant) {
    this.tenant = tenant;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
