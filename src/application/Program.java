package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Room Number:\t");
		int number = sc.nextInt();
		System.out.print("Check-In Date (DD/MM/YYYY):\t");
		Date checkin = sdf.parse(sc.next());
		System.out.print("Check-Out Date (DD/MM/YYYY):\t");
		Date checkout = sdf.parse(sc.next());
		
		if(!checkout.after(checkin)) {
			System.out.println("Error in Reservation: Check-Out date must be after Check-In date!");
		}
		else {
			Reservation reservation = new Reservation(number,checkin,checkout);
			System.out.println("Reservation: " + reservation);
			
			System.out.println("\nEnter data to update the reservation:");
			System.out.print("Check-In Date (DD/MM/YYYY):\t");
			checkin = sdf.parse(sc.next());
			System.out.print("Check-Out Date (DD/MM/YYYY):\t");
			checkout = sdf.parse(sc.next());
			
			String error = reservation.updateDates(checkin, checkout);
			if(error != null) {
				System.out.println("Error: " + error);
			}
			else {
				System.out.println("Reservation: " + reservation); 
			}
		}
		sc.close();
		
		
	}

}
