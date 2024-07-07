#cmake_minimum_required(VERSION 3.21)
#project(model3)
#
#set(CMAKE_CXX_STANDARD 20)
#
#add_executable(model3 main.cpp CRUD/domain.cpp CRUD/domain.h CRUD/repository.cpp CRUD/repository.h CRUD/exceptions.h CRUD/tests.cpp CRUD/tests.h CRUD/service.cpp CRUD/service.h)
cmake_minimum_required(VERSION 3.5)

project(model3 VERSION 0.1 LANGUAGES CXX)

set(CMAKE_INCLUDE_CURRENT_DIR ON)

set(CMAKE_AUTOUIC ON)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

find_package(QT NAMES Qt6 Qt5 REQUIRED COMPONENTS Widgets)
find_package(Qt${QT_VERSION_MAJOR} REQUIRED COMPONENTS Widgets)

set(PROJECT_SOURCES
        main.cpp
        CRUD/domain.h CRUD/repository.h CRUD/repository.cpp CRUD/exceptions.h CRUD/service.cpp CRUD/service.h CRUD/tests.cpp CRUD/tests.h GUI.cpp GUI.h includesQT.h model.h observer.h)

if(${QT_VERSION_MAJOR} GREATER_EQUAL 6)
    qt_add_executable(model3
            MANUAL_FINALIZATION
            ${PROJECT_SOURCES}
            )
    # Define target properties for Android with Qt 6 as:
    #    set_property(TARGET model3 APPEND PROPERTY QT_ANDROID_PACKAGE_SOURCE_DIR
    #                 ${CMAKE_CURRENT_SOURCE_DIR}/android)
    # For more information, see https://doc.qt.io/qt-6/qt-add-executable.html#target-creation
else()
    if(ANDROID)
        add_library(model3 SHARED
                ${PROJECT_SOURCES}
                )
        # Define properties for Android with Qt 5 after find_package() calls as:
        #    set(ANDROID_PACKAGE_SOURCE_DIR "${CMAKE_CURRENT_SOURCE_DIR}/android")
    else()
        add_executable(model3
                ${PROJECT_SOURCES}
                )
    endif()
endif()

target_link_libraries(model3 PRIVATE Qt${QT_VERSION_MAJOR}::Widgets)

set_target_properties(model3 PROPERTIES
        MACOSX_BUNDLE_GUI_IDENTIFIER my.example.com
        MACOSX_BUNDLE_BUNDLE_VERSION ${PROJECT_VERSION}
        MACOSX_BUNDLE_SHORT_VERSION_STRING ${PROJECT_VERSION_MAJOR}.${PROJECT_VERSION_MINOR}
        MACOSX_BUNDLE TRUE
        WIN32_EXECUTABLE TRUE
        )

if(QT_VERSION_MAJOR EQUAL 6)
    qt_finalize_executable(model3)
endif()
