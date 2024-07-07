import Test.repo
import Test.domain_validator
import Test.service_filme
import Test.service_clienti
import Test.repo_film
import Test.repo_client
import Test.service_inchirieri
import Test.testgenerate


def runall():
    Test.domain_validator.runall()
    Test.service_filme.runall()
    Test.service_clienti.runall()
    Test.repo_film.runall()
    Test.repo_client.runall()
    Test.service_inchirieri.runall()
    Test.testgenerate.runall()
