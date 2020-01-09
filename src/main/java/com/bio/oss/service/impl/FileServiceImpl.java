package com.bio.oss.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bio.common.base.CoreServiceImpl;
import com.bio.common.config.BiodashboardConfig;
import com.bio.common.utils.DateUtils;
import com.bio.common.utils.FileType;
import com.bio.oss.dao.FileDao;
import com.bio.oss.domain.FileDO;
import com.bio.oss.sdk.QiNiuOSSService;
import com.bio.oss.service.FileService;

/**
 * <pre>
 * </pre>
 * 
 * <small> 2018年3月23日 | Aron</small>
 */
@Service
public class FileServiceImpl extends CoreServiceImpl<FileDao, FileDO> implements FileService {

    @Autowired
    private BiodashboardConfig ifastConfig;
    @Autowired
    private QiNiuOSSService qiNiuOSS;

    @Override
    public String upload(byte[] uploadBytes, String fileName) {
        fileName = fileName.substring(0, fileName.indexOf(".")) + "-" + System.currentTimeMillis() + fileName.substring(fileName.indexOf("."));
        fileName = ifastConfig.getProjectName() + "/" + DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN_8)
                + "/" + fileName;
        String url = qiNiuOSS.upload(uploadBytes, fileName);
        FileDO sysFile = new FileDO(FileType.fileType(fileName), url, new Date());
        super.insert(sysFile);
        return url;
    }
}
