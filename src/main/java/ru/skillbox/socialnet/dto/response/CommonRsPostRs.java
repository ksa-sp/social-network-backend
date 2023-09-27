package ru.skillbox.socialnet.dto.response;

import lombok.Data;

@Data
public class CommonRsPostRs {
    private PostRs data;
    private Integer itemPerPage;
    private Integer offset;
    private Integer perPage;
    private Long timestamp;
    private Long total;
}
