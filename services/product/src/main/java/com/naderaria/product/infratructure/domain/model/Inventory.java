package com.naderaria.product.infratructure.domain.model;

import com.naderaria.common_data.domin.BaseEntity;
import com.naderaria.product.infratructure.domain.exception.InvalidInventoryQuantityException;
import com.naderaria.product.infratructure.domain.exception.NotEnoughInventoryException;
import com.naderaria.product.infratructure.domain.exception.ReleaseInventoryException;
import com.naderaria.product.infratructure.domain.exception.ReserveInventoryException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tb_inventory")
@SequenceGenerator(name = "sequence-generator", sequenceName = "invt_seq", allocationSize = 1)
@SuperBuilder
@NoArgsConstructor
public class Inventory extends BaseEntity {

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "reserved_quantity")
    private int reservedQuantity;// تعداد کالاهای رزرو شده

    public int getAvailableQuantity() {
        return this.quantity - this.reservedQuantity;
    }

    public final void increase(int quantity) {
        if (quantity <= 0) {
            throw new InvalidInventoryQuantityException();//"quantity must be greater than zero"
        }
        this.quantity = (this.quantity + quantity);
    }

    public final void decrease(int quantity) {
        if (quantity <= 0) {
            throw new InvalidInventoryQuantityException();//"quantity must be greater than zero"
        }
        if (quantity > this.getAvailableQuantity()) {
            throw new NotEnoughInventoryException();
        }
        this.quantity = (this.quantity - quantity);
    }

    public final void reserve(int quantity) {
        if (quantity <= 0) {
            throw new InvalidInventoryQuantityException();//"quantity must be greater than zero"
        }
        if (this.getAvailableQuantity() < quantity) {
            throw new ReserveInventoryException();
        }
        this.reservedQuantity = (this.reservedQuantity + quantity);
    }

    public final void release(int quantity) {
        if (quantity <= 0) {
            throw new InvalidInventoryQuantityException();//"quantity must be greater than zero"
        }
        if (this.reservedQuantity < quantity) {
            throw new ReleaseInventoryException();
        }
        this.reservedQuantity = (this.reservedQuantity - quantity);
    }

    public final boolean isAvailable() {
        return this.getAvailableQuantity() > 0;
    }

    public final boolean isOutOfStock() {
        return this.getAvailableQuantity() <= 0;
    }

    public final void validateReservation(int quantity) {
        if (!hasEnough(quantity)) {
            throw new ReserveInventoryException();
        }
    }

    public boolean hasEnough(int quantity) {
        return getAvailableQuantity() >= quantity;
    }
}