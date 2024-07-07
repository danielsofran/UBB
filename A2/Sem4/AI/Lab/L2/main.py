from network import Network

def test_all(f, tests):
    for test in tests:
        f(test)

def solve(file: str):
    network = Network()
    network.read(file)
    network.greedyCommunitiesDetectionNewmanNewmanV2()
    network.title = file + f" NewmanNewman"
    network.plot()

def solveWithExpected(file: str):
    network = Network()
    network.read(file)
    network.greedyCommunitiesDetectionNewmanNewmanWithNrCom()
    network.title = file + f" NewmanNewman"
    network.plot()


def solveTool(file: str):
    network = Network()
    network.read(file)
    network.title = file + " NewmanGirvan"
    network.greedyCommunitiesDetectionNewmanGirvan()
    network.plot()
    # network.title = file + ' real.dat'
    # network.communities = network.expected_communities
    # network.plot()

test_set = ['demo', 'karate', 'krebs', 'dolphins', 'football', 'windmill0', 'windmill1', 'caveman', 'prt0', 'prt1', 'prt2']
# test_all(solve, test_set)

half_nr_tests = int(len(test_set)/2)
test_all(solveWithExpected, test_set[half_nr_tests:])

# test_name = 'football'
# solve(test_name)
# # solveTool(test_name)

