package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {

	//O atributo é estático para que não seja instanciado um novo SimpleDateFormat
	//para cada nova instância de Reservation
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Integer roomNumber;
	private Date checkin;
	private Date checkout;
	
	public Reservation() {
	}
	
	public Reservation(Integer roomNumber, Date checkin, Date checkout) {
		if(!checkout.after(checkin)) {
			throw new DomainException("Error in Reservation: Check-Out date must be after Check-In date!");
		}
		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckin() {
		return checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	
	public long duration() {
		long diff = checkout.getTime() - checkin.getTime();
		return TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
	}
	
	public void updateDates(Date checkin, Date checkout) {
		Date now = new Date();
		
		if(checkin.before(now) || checkout.before(now)) {
			throw new DomainException("Error in Reservation: Reservation dates for update must be future dates!");
		}
		else if(!checkout.after(checkin)) {
			throw new DomainException("Error in Reservation: Check-Out date must be after Check-In date!");
		}
		this.checkin = checkin;
		this.checkout = checkout;
	}
	
	@Override
	public String toString() {
		return "Room: " + roomNumber + ", Check-In: " + sdf.format(checkin) + ", Check-Out: " + sdf.format(checkout)
		+ ", " + duration() + " nights";
	}
}
