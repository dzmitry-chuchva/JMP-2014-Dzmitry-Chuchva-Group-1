package com.epam.nikitasidorevich.banksystem.entity.currency;

public class CurrencyTO {
    private Long id;
    private String code;
    private String fullName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyTO)) return false;

        CurrencyTO that = (CurrencyTO) o;

        if (!code.equals(that.code)) return false;
        if (!fullName.equals(that.fullName)) return false;
        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }
}
