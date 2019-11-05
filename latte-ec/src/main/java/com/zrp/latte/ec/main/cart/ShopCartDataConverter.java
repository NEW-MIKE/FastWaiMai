package com.zrp.latte.ec.main.cart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zrp.latte.ui.recycler.DataConverter;
import com.zrp.latte.ui.recycler.ItemType;
import com.zrp.latte.ui.recycler.MultipleFields;
import com.zrp.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

public class ShopCartDataConverter extends DataConverter {

	@Override
	public ArrayList<MultipleItemEntity> convert() {
		final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
		final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");

		final int size = dataArray.size();
		for(int i = 0; i < size; i++ ){
			final JSONObject data = dataArray.getJSONObject(i);
			final Float price = data.getFloatValue("price");
			final String id = data.getString("id");
			final Integer count = data.getIntValue("count");
			final String title = data.getString("title");
			final String desc = data.getString("desc");
			final String thumb = data.getString("thumb");

			final MultipleItemEntity entity = MultipleItemEntity.builder()
					.setField(MultipleFields.ITEM_TYPE, ShopCartItemType.SHOP_CART_ITEM)
					.setField(MultipleFields.ID, Integer.valueOf(id))
					.setField(MultipleFields.IMAGE_URL, thumb)
					.setField(ShopCartItemFields.COUNT, count)
					.setField(ShopCartItemFields.DESC, desc)
					.setField(ShopCartItemFields.PRICE, price)
					.setField(ShopCartItemFields.TITLE, title)
					.setField(ShopCartItemFields.IS_SELECTED, false)
					.setField(ShopCartItemFields.POSITION, i)
					.build();
			dataList.add(entity);
		}

		return dataList;
	}
}