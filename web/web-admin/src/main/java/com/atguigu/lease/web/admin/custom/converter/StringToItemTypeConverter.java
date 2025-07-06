package com.atguigu.lease.web.admin.custom.converter;

import com.atguigu.lease.model.enums.ItemType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToItemTypeConverter implements Converter<String, ItemType> {
    @Override
    public ItemType convert(String code) {
//        if ("1".equals(code)) {
//            return ItemType.APARTMENT;
//        }else if ("2".equals(code)) {
//            return ItemType.ROOM;
//        }
//        ItemType.class.getEnumConstants();
        ItemType[] values = ItemType.values();
        for (ItemType itemType : values) {
            if (itemType.getCode().equals(Integer.valueOf(code))) {
                return itemType;
            }
        }

        throw new IllegalArgumentException("code isn`t right");
    }
}
