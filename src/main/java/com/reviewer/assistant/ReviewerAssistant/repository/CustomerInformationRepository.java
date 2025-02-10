package com.reviewer.assistant.ReviewerAssistant.repository;


import com.reviewer.assistant.ReviewerAssistant.entity.CustomerInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerInformationRepository extends JpaRepository<CustomerInformation,Long> {

    List<CustomerInformation> findByFirstName(String firstName);
    List<CustomerInformation> findByLastName(String firstName);


}
