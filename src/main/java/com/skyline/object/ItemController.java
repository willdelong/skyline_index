package com.skyline.object;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.skyline.SkylineProjectApplication;

@RestController
public class ItemController {

	@RequestMapping(path = "/items", method = RequestMethod.GET, produces = "application/json")
	public List<String> getAll() {

		return SkylineProjectApplication.items;

	}

	@RequestMapping(path = "/items/{id}", method = RequestMethod.GET, produces = "application/json")
	public String getItem(@PathVariable("id") int id) {

		if (id < SkylineProjectApplication.items.size())
			return SkylineProjectApplication.items.get(id);
		else
			return "Id is out of range";

	}

}