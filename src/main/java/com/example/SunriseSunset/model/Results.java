package com.example.SunriseSunset.model;
import com.fasterxml.jackson.annotation.JsonProperty;


    public class Results {
        private String sunrise;
        private String sunset;

        @JsonProperty("sunrise")
        public String getSunrise() {
            return sunrise;
        }

        @JsonProperty("sunset")
        public String getSunset() {
            return sunset;
        }
    }

