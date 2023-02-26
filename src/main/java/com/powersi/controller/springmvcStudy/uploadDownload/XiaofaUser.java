package com.powersi.controller.springmvcStudy.uploadDownload;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.sql.rowset.BaseRowSet;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ExcelIgnoreUnannotated
public class XiaofaUser extends BaseRowSet implements Serializable {

    private static final long serialVersionUID = -4687614560160536850L;

    @ExcelProperty(value = {"id"}, index = 0)
    private Long id;

    @ExcelProperty(value = {"userName"}, index = 1)
    private String userName;

    @ExcelProperty(value = {"password"}, index = 2)
    private String password;

    /**
     * 是否被删除。0是未删除，1是删除
     */
    @ExcelProperty(value = {"phone"}, index = 3)
    private String phone;

    @ExcelProperty(value = {"source"}, index = 4)
    private String source;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
