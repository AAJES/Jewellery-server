package com.ajes.model;

import lombok.Data;

@Data
public class TranslationRequest {
    private String text;
    private String targetLanguage;
}
