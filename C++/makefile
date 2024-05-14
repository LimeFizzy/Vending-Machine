# Compiler flags
CFLAGS := -std=c++2b -Wall -Wextra -Wpedantic
# Source files
SRCS := main.cpp VendingMachine.cpp Product.cpp
# Object files
OBJS := $(SRCS:.cpp=.o) 
# Executable file
EXE := test

# Build executable
$(EXE): $(OBJS)
	g++ $(CFLAGS) $(OBJS) -o $(EXE)

# Compile source files to object files
%.o: %.cpp
	g++ $(CFLAGS) -c $< -o $@

# Clean object files and executable
clean:
	rm -f $(OBJS) $(EXE) *.bin

# Rebuild
rebuild: clean $(EXE)
