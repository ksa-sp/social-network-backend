package ru.skillbox.socialnet.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class LikeRs {
    private Integer likes;
    private List<Integer> users;
}
