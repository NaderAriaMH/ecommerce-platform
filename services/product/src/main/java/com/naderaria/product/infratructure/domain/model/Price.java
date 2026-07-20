package com.naderaria.product.infratructure.domain.model;

import com.naderaria.common_data.domin.BaseEntity;
import com.naderaria.product.infratructure.domain.exception.*;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "tb_price")
@SequenceGenerator(name = "sequence-generator", sequenceName = "price_seq", allocationSize = 1)
@SuperBuilder
@NoArgsConstructor
public class Price extends BaseEntity {

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "discount")
    private Integer discount;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_currency")
    private Currency currency;

    public final void changePrice(BigDecimal amount, Currency currency) {

        if (amount == null) throw new InvalidPriceException();//"amount cannot be null"

        if (currency == null) throw new InvalidCurrencyException();//"currency cannot be null"

        if (amount.signum() <= 0) throw new InvalidPriceException();//"Amount must be greater than zero"

        this.amount = amount;
        this.currency = currency;
    }

    public final void applyDiscount(Integer discount) {
        if (discount == null) throw new DiscountCannotBeNullException();
        if (discount < 0) {
            throw new DiscountCannotBeNegativeException();
        }
        if (discount > 100) {
            throw new UnreasonableDiscountException();
        }
        this.discount = discount;
    }

    public final BigDecimal getFinalPrice() {
        return amount.subtract(
                amount.multiply(BigDecimal.valueOf(discount))
                      .divide( BigDecimal.valueOf(100),this.currency.getFractionDigits(),RoundingMode.HALF_UP)
        );
    }

    public final void removeDiscount() {
        this.discount = 0;
    }

    public final boolean hasDiscount() {
        if (discount == null) throw new DiscountCannotBeNullException();
        return this.discount > 0;
    }

    public final void increasePrice(BigDecimal amount) {
        if (amount == null) throw new InvalidPriceException();//"amount cannot be null"
        if (amount.signum() <= 0) throw new InvalidPriceException();//"Amount must be greater than zero"
        this.amount = this.amount.add(amount);
    }

    public final void decreasePrice(BigDecimal amount) {
        if (amount == null) throw new InvalidPriceException();//"amount cannot be null"
        if (amount.signum() <= 0) throw new InvalidPriceException();//"Amount must be greater than zero"
        BigDecimal newAmount = this.amount.subtract(amount);

        if(newAmount.signum() <= 0){
            throw new InvalidPriceException();
        }

        this.amount = newAmount;
    }

}