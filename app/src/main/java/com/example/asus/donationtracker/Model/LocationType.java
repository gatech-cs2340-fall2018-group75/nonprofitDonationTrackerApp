package com.example.asus.donationtracker.Model;

public enum LocationType {
        TYPE1 ("Type1"),
        TYPE2 ("Type2"),
        Type3 ("Type3");

        private final String type;

        LocationType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public String toString() {
            return type;
        }

        //TODO:Fill in with actual location types
}
