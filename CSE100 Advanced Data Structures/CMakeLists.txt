cmake_minimum_required(VERSION 3.13)
project(CSE100)

set(CMAKE_CXX_STANDARD 14)

include_directories(PA1/BST)
include_directories(PA1/KDT)
include_directories(trash)

add_executable(CSE100
        PA1/BST/benchmark_BST.cpp
        PA1/BST/BSA.hpp
        PA1/BST/BST.hpp
        PA1/BST/BSTIterator.hpp
        PA1/BST/BSTNode.hpp
        PA1/BST/test_BST.cpp
        PA1/BST/Timer.hpp
        PA1/KDT/BruteForceKNN.hpp
        PA1/KDT/KDT.hpp
        PA1/KDT/KNNClassifier.cpp
        PA1/KDT/Point.hpp
        PA1/KDT/runtime.cpp
        PA1/KDT/test_KDT.cpp
        PA1/KDT/Timer.hpp
        trash/benchmark_BST.cpp
        trash/BSA.hpp
        trash/BST.hpp
        trash/BSTIterator.hpp
        trash/BSTNode.hpp
        trash/test_BST.cpp
        trash/Timer.hpp
        main.cpp)
