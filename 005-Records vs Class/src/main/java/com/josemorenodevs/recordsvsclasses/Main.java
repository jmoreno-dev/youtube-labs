package com.josemorenodevs.recordsvsclasses;

import com.josemorenodevs.recordsvsclasses.domain.Order;

public class Main {

	public static void main(String[] args) {
		Order order = new Order(1L);

		System.out.println(order.getStatus());

		order.confirm();

		System.out.println(order.getStatus());

		order.ship();

		System.out.println(order.getStatus());
	}
}
