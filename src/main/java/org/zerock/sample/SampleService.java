package org.zerock.sample;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@ToString
public class SampleService {

    @Autowired
    private SampleDao sampleDao;
}
