package com.neo.project.Configuration;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component("singleCache")
@Data
public class SingleCacheStrategy implements CacheStrategy {

        String name="hi";
}
