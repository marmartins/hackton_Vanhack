package com.marcsystem.std.company.model;

public enum Status {

    OPEN{
        @Override
        Status next() {
            return APPROVED;
        }

        @Override
        public String toString() {
            return "Open";
        }
    },
    APPROVED{
        @Override
        Status next() {
            return SENT;
        }

        @Override
        public String toString() {
            return "Approved";
        }
    },
    SENT {
        @Override
        Status next() {
            return COMPLETED;
        }

        @Override
        public String toString() {
            return "Sent";
        }
    },
    COMPLETED {
        @Override
        Status next() {
            return null;
        }

        @Override
        public String toString() {
            return "Completed";
        }
    },
    CANCELED{
        @Override
        Status next() {
            return null;
        }

        @Override
        public String toString() {
            return "Canceled";
        }
    };

    abstract Status next();

}
