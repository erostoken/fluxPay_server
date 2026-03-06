package com.fluxpay.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fluxpay.core.entity.ApiCategory;
import com.fluxpay.core.service.ApiCategoryService;
import com.fluxpay.core.mapper.ApiCategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author apple
* @description 针对表【api_category(API 分类表)】的数据库操作Service实现
* @createDate 2026-03-06 09:57:35
*/
@Service
public class ApiCategoryServiceImpl extends ServiceImpl<ApiCategoryMapper, ApiCategory>
    implements ApiCategoryService{

}




