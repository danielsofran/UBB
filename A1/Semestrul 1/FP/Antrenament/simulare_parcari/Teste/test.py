from Domain.parcare import Parcare
from Repository.repository import RepoParcari
from Service.service import ServiceParcari


class Test:
    """
        clasa statica pt teste
    """
    @staticmethod
    def __test_parcare():  # test domeniu
        p = Parcare(1, "nm", "str", 3)
        assert p.id == 1
        assert p.nume == "nm"
        assert p.strada == "str"
        assert p.numar_utilizari == 3
        assert str(p) == "Parcarea cu id 1, numele: nm, strada: str, numarul de utilizari: 3"
        assert isinstance(Parcare.fromStr("1,nm,str,3"), Parcare)

    @staticmethod
    def __test_repo():  # test stocare parcari
        repo = RepoParcari("Teste/parcari.txt")
        assert len(repo.get_all()) == 3
        assert repo.get_all()[0].id == 97
        assert repo.get_all()[1].nume == "D3X2"
        assert repo.get_all()[2].strada == "Dorobantilor"
        assert repo.get_all()[0].numar_utilizari == 9

    @staticmethod
    def __test_service():  # test manager parcari
        repo = RepoParcari("Teste/parcari.txt")
        service = ServiceParcari(repo)
        locuri = service.locuri_de_pe_o_strada("Dorobantilor")
        assert len(locuri) == 2
        assert locuri[0].id == 7
        assert locuri[0].nume == "W904"
        assert locuri[0].strada == "Dorobantilor"
        assert locuri[0].numar_utilizari == 17
        cele_mai_utilizate_locuri = service.cel_mai_utilizat_loc_per_strada()
        assert cele_mai_utilizate_locuri.keys() == {"Dorobantilor", "Teodor Mihali"}
        assert cele_mai_utilizate_locuri.values().__len__() == 2
        assert cele_mai_utilizate_locuri["Dorobantilor"].nume == "W904"
        assert cele_mai_utilizate_locuri["Teodor Mihali"].nume == "D3X2"

    @staticmethod
    def run_all():  # ruleaza toate testele
        Test.__test_parcare()
        Test.__test_repo()
        Test.__test_service()
