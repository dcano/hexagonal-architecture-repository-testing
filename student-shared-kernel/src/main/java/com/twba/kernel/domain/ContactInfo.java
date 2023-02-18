package com.twba.kernel.domain;

import com.twba.kernel.fwk.ValueObject;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ContactInfo extends ValueObject {

    @NotNull
    private final List<String> phoneNumbers;

    @NotNull
    private final String emailAddress;

    private ContactInfo(List<String> phoneNumbers, String emailAddress) {
        this.phoneNumbers = phoneNumbers;
        this.emailAddress = Objects.nonNull(emailAddress)?emailAddress.toLowerCase():null;
        this.validate();
    }

    public static ContactInfo with(String emailAddress, String... phoneNumber) {
        return new ContactInfo(Arrays.asList(phoneNumber), emailAddress);
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInfo that = (ContactInfo) o;
        return Objects.equals(phoneNumbers, that.phoneNumbers) &&
                Objects.equals(emailAddress, that.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumbers, emailAddress);
    }

    public Optional<String> getMainPhoneNumber() {
        return phoneNumbers.stream().findFirst();
    }
}
