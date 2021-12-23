package com.mslc;

public class Principal {

	// Datos Entorno
	static int contagiados;
	static int nivel_pandemia;

	// Datos Transporte
	final static int precio_entrada = 20;
	static double aforo_transporte;
	static int plazas_disponibles;
	static int num_plazas;
	static int plazas_ocupadas_prof;
	static int plazas_ocupadas_noProf;
	static int plazas_reservadasProf;

	public static int main(String[] args) throws NoHayPlazasDisponiblesException, NoPosibilidadDeViajarException {

		Persona pasajero = new Persona("Juan", 18, true, false, false, true, false);
		principal(100, 500, 50, 80, pasajero);
		
		return 0;
	}

	public static double principal(int contag, int nPlazas, int plazas_ocupProf, int plazas_ocupNoProf, Persona p)
			throws NoHayPlazasDisponiblesException, NoPosibilidadDeViajarException {
		contagiados = contag;
		num_plazas = nPlazas;
		plazas_ocupadas_prof = plazas_ocupProf;
		plazas_ocupadas_noProf = plazas_ocupNoProf;
		contextoPandemia();

		// imprimir precio
		return comprobarSiPuedeViajar(p);
	}

	// Contexto Pandemia
	private static void contextoPandemia() {
		if (contagiados < 100) {
			aforo_transporte = 1;
			nivel_pandemia = 0;
		} else if (contagiados >= 100 && contagiados <= 200) {
			aforo_transporte = 0.8;
			nivel_pandemia = 1;
		} else if (contagiados > 200 && contagiados <= 300) {
			aforo_transporte = 0.6;
			nivel_pandemia = 2;
		} else if (contagiados > 300 && contagiados <= 500) {
			aforo_transporte = 0.4;
			nivel_pandemia = 3;
		} else {
			aforo_transporte = 0.3;
			nivel_pandemia = 4;
		}
	}

	// Comprobar si puede viajar
	private static double comprobarSiPuedeViajar(Persona p1)
			throws NoHayPlazasDisponiblesException, NoPosibilidadDeViajarException {
		if (!p1.isSano() || p1.isContacto() || p1.isSintomas() || !p1.isPasaporte_COVID()) {
			throw new NoPosibilidadDeViajarException("Lo siento " + p1.getNombre() + ", usted no puede viajar");
		} else {
			plazas_disponibles = (int) (num_plazas * aforo_transporte);
			System.out.println("Quedan " + plazas_disponibles + " plazas");
			System.out.println();

			double descuento_incremento = DescuentoIncremento(p1);
			double precioB= precioBillete(p1, descuento_incremento);
			System.out.printf("Su entrada cuesta %.2f €", precioB);
			return precioB;
		}
	}

	private static double DescuentoIncremento(Persona p1) throws NoPosibilidadDeViajarException {
		double descuento = 1;
		if (nivel_pandemia == 0) {
			if (p1.getEdad() < 23) {
				descuento = 1 - 0.6;
				System.out.println("Usted tiene un descuento del 60%");
			} else if (p1.getEdad() > 65) {
				descuento = 1 - 0.8;
				System.out.println("Usted tiene un descuento del 80%");
			}
		} else if (nivel_pandemia == 1) {
			if (p1.getEdad() < 23) {
				descuento = 1 - 0.3;
				System.out.println("Usted tiene un descuento del 30%");
			} else if (p1.getEdad() > 65) {
				descuento = 1 - 0.5;
				System.out.println("Usted tiene un descuento del 50%");
			}
		} else if (nivel_pandemia == 2) {
			if (p1.getEdad() > 65) {
				descuento = 1.2;
				System.out.println("Usted tiene un descuento del 20%");
			}
			plazas_reservadasProf = (int) (0.6 * plazas_disponibles);
		} else if (nivel_pandemia == 3) {
			if (p1.getEdad() < 23) {
				descuento = 1.2;
				System.out.println("Usted tiene un incremento del 20%");
			} else if (p1.getEdad() > 65) {
				descuento = 1.5;
				System.out.println("Usted tiene un incremento del 50%");
			}
			plazas_reservadasProf = (int) (0.8 * plazas_disponibles);
		} else {
			if (p1.getEdad() < 23) {
				descuento = 1.5;
				System.out.println("Usted tiene un incremento del 50%");
			} else if (p1.getEdad() > 65) {
				throw new NoPosibilidadDeViajarException("Lo siento " + p1.getNombre() + ", usted no puede viajar");
			}
			plazas_reservadasProf = (int) (0.9 * plazas_disponibles);
		}
		return descuento;
	}

	public static double precioBillete(Persona p1, double descuento)
			throws NoHayPlazasDisponiblesException, NoPosibilidadDeViajarException {
		if ((plazas_ocupadas_prof + plazas_ocupadas_noProf) < plazas_disponibles) {
			if (nivel_pandemia < 1) {
				return precio_entrada * descuento;
			} else {
				// ASIENTOS LIBRES Y PRECIO
				int num_disponibles_profesionales = plazas_reservadasProf;
				int num_disponibles_noProfesionales = plazas_disponibles - plazas_reservadasProf;

				if (p1.isProfesional()) {
					if (plazas_ocupadas_prof < num_disponibles_profesionales) {
						return precio_entrada * descuento;
					} else {
						throw new NoHayPlazasDisponiblesException("Lo siento " + p1.getNombre() + ", no quedan plazas para profesionales");
					}
				} else {
					if (plazas_ocupadas_noProf < num_disponibles_noProfesionales) {
						return precio_entrada * descuento;
					} else {
						throw new NoHayPlazasDisponiblesException("Lo siento " + p1.getNombre() + ", no quedan plazas para no profesionales");
					}
				}
			}
		} else {
			throw new NoHayPlazasDisponiblesException("Lo siento " + p1.getNombre() + ", no hay plazas disponibles");
		}
	}
}
