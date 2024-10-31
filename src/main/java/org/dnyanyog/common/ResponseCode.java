package org.dnyanyog.common;

public enum ResponseCode {
  VACANCY_ADDED("Success", "Vacancy added successfully!"),
  VACANCY_ALREADY_EXISTS("Fail", "Vacancy already exists for this tenant!"),
  VACANCY_NOT_FOUND("Fail", "Vacancy not found!"),
  VACANCY_UPDATED("Success", "Vacancy updated successfully!"),
  VACANCY_RETRIEVED("Success", "Vacancy retrieved successfully!"),
  VACANCY_CANCELLED("Success", "Vacancy cancelled successfully!"),
  VACANCY_INVALID_STATUS("Fail", "Invalid status provided for vacancy!");

  private final String status;
  private final String message;

  ResponseCode(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
