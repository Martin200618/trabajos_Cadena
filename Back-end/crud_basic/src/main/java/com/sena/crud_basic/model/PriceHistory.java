package com.sena.crud_basic.model;

import jakarta.persistence.*;

@Entity(name="price_history")
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="history_id")
        private int historyId;

    @Column(name="price", nullable=false)
        private double price;

    public PriceHistory(int historyId, double price) {
        this.historyId = historyId;
        this.price = price;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
