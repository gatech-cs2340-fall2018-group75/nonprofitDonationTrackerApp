package com.example.asus.donationtracker.Model;

public enum LocationType {
        DR ("Drop Off"),
        ST ("Store"),
        WA ("Warehouse");

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
