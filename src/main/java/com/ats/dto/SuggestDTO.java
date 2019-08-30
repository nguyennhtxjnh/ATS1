package com.ats.dto;

import com.ats.entity.Job;
import lombok.Data;

@Data
public class SuggestDTO implements Comparable<SuggestDTO> {
    int sum;
    Job job;

    @Override
    public int compareTo(SuggestDTO o) {
        return Integer.compare(this.getSum(), o.getSum());
    }
}
