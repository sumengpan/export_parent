package com.smp.service.company;

import com.github.pagehelper.PageInfo;
import com.smp.domain.company.Company;

import java.util.List;

public interface ICompanyService {
    List<Company> findAll();

    void saveCompany(Company company);

    void deleteById(String id);

    Company findById(String id);

    void updateCompany(Company company);

    PageInfo<Company> findPage(int currentPage, int pageSize);
}
