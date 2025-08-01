package com.statoverflow.status.domain.attribute.dto;

import java.util.ArrayList;
import java.util.List;

import com.statoverflow.status.domain.master.entity.Attribute;
import com.statoverflow.status.domain.master.entity.MainQuest;
import com.statoverflow.status.domain.master.entity.MainSubQuest;
import com.statoverflow.status.domain.quest.entity.UsersMainQuest;
import com.statoverflow.status.domain.quest.entity.UsersSubQuest;

public record AttributeDto(
	int id,
	String name,
	int exp
) {

	public static List<AttributeDto> fromMainQuest(MainQuest mainQuest){
		List<AttributeDto> attributeDtos = new ArrayList<>();

		attributeDtos.add(new AttributeDto(
			mainQuest.getAttribute1().getId(),
			mainQuest.getAttribute1().getName(),
			mainQuest.getExp1()
		));

		if (mainQuest.getAttribute2() != null) {
			attributeDtos.add(new AttributeDto(
				mainQuest.getAttribute2().getId(),
				mainQuest.getAttribute2().getName(),
				mainQuest.getExp2()
			));
		}
		return attributeDtos;
	}


	public static List<AttributeDto> fromMainSubQuest(MainSubQuest mainSubQuest){
		List<AttributeDto> attributeDtos = new ArrayList<>();

		attributeDtos.add(new AttributeDto(
			mainSubQuest.getAttribute1().getId(),
			mainSubQuest.getAttribute1().getName(),
			mainSubQuest.getExp1()
		));

		if (mainSubQuest.getAttribute2() != null) {
			attributeDtos.add(new AttributeDto(
				mainSubQuest.getAttribute2().getId(),
				mainSubQuest.getAttribute2().getName(),
				mainSubQuest.getExp2()
			));
		}
		return attributeDtos;
	}

	public static List<AttributeDto> fromUsersSubQuest(UsersSubQuest usersSubQuest){
		List<AttributeDto> attributeDtos = new ArrayList<>();

		attributeDtos.add(new AttributeDto(
			usersSubQuest.getAttribute1().getId(),
			usersSubQuest.getAttribute1().getName(),
			usersSubQuest.getExp1()
		));

		if (usersSubQuest.getAttribute2() != null) {
			attributeDtos.add(new AttributeDto(
				usersSubQuest.getAttribute2().getId(),
				usersSubQuest.getAttribute2().getName(),
				usersSubQuest.getExp2()
			));
		}
		return attributeDtos;
	}

	public static List<AttributeDto> fromUsersMainQuest(UsersMainQuest usersMainQuest){
		List<AttributeDto> attributeDtos = new ArrayList<>();

		attributeDtos.add(new AttributeDto(
			usersMainQuest.getAttribute1().getId(),
			usersMainQuest.getAttribute1().getName(),
			usersMainQuest.getExp1()
		));

		if (usersMainQuest.getAttribute2() != null) {
			attributeDtos.add(new AttributeDto(
				usersMainQuest.getAttribute2().getId(),
				usersMainQuest.getAttribute2().getName(),
				usersMainQuest.getExp2()
			));
		}
		return attributeDtos;
	}
}