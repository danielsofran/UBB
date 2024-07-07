def creeaza_oras(nume, lat, long):
	return {
		"nume":  nume,
		"lat": lat,
		"long": long
	}
	#return [nume, lat, long]

def get_nume(oras):
	return oras["nume"]

def get_lat(oras):
	return oras["lat"]

def get_long(oras):
	return oras["long"]

def egal_oras(oras0, oras1):
	return oras0["nume"] == oras1["nume"]

def add_oras_lista(oras, l):
	for _oras in l:
		if egal_oras(_oras, oras):
			raise Exception("oras existent")
	l.append(oras)

def test_add_oras_lista():
	l = []
	oras = creeaza_oras("Lyon", 23.45, 45.67)
	assert(len(l) == 0)
	add_oras_lista(oras, l)
	assert(len(l) == 1)
	try:
		add_oras_lista(oras, l)
		assert(False)
	except Exception as ex:
		assert(str(ex) == "oras existent!")

def add_oras_in_lista_orase(nume, lat, long, l):
	oras = creeaza_oras(nume, lat, long)
	add_oras_lista(oras, l)

def ui_add_oras(l):

	print("intrare oras")
	nume = input("Introduceti numele orasului:")
	try:
		lat = float(input("Introduceti latitudinea:"))
		long = float(input("Introduceti longitudinea:"))
		add_oras_in_lista_orase(nume, lat, long, l)
	except ValueError:
		print("invalid real value!")
	except Exception as ex:
		print(ex)

def test_add_oras_in_lista_orase():
	l = []
	assert(len(l) == 0)
	add_oras_in_lista_orase("Constanta",  23.43, 45.67, l)
	assert(len(l) == 1)
	assert(get_nume(l[0]) == "Constanta")
	assert(abs(get_lat(l[0]) - 23.34) < 0.0001)
	assert(abs(get_long(l[0]) - 45.67) < 0.0001) 

def run_teste():
	test_add_oras_in_lista_orase()
	test_add_oras_lista()

def run():
	l = []
	while True:
		cmd = input(">>>")
		if cmd == "exit":
			return
		if cmd == "add_oras":
			ui_add_oras(l)
		else:
			print("comanda invalida")
run_teste()
run()
