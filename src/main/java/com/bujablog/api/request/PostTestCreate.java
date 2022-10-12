package com.bujablog.api.request;

import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
public class PostTestCreate {
    public String title;
    public String content;
}
