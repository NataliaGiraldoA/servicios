package co.edu.usbcali.records.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingAddress {
    private Integer shippingAddressId;

    private String address; /*PK*/
    private String city;
    private String state;
    private String zip;
    private String country;
    private boolean is_default;
}
