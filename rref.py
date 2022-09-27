# row = int(input())
# col = int(input())
# matrix=[[0 for j in range(col)]for i in range (row)]
# for i in range (row):
#     for j in range (col):
#         matrix[i][j]=int(input())
import sys
def elmCol (matrix,col):
    temp = [[0 for j in range (len(matrix))] for i in range (len(matrix[0])-1)]
    for i in range (len(matrix)):
        for j in range(col):
            temp[i][j] = matrix[i][j]
        for j in range(col+1,len(matrix[0])):
            temp[i][j] = matrix[i][j]

def swap(matrix,r1,r2):
    temp=matrix[r1]
    matrix[r1]=matrix[r2]
    matrix[r2]=temp
    return matrix


def sortZero(matrix,col,r1):
    for i in range (r1,len(matrix)-1):
        if matrix[i][col]==0 and matrix[i+1][col]!=0:
            swap(matrix,i,i+1)
    return matrix


matrix1 = [
    [1,1,-1,-1,1],
    [2,5,-7,-5,-2],
    [2,-1,1,3,4],
    [5,2,-4,2,6]
]
matrix2 =[ 
    [1,-1,0,0,1,3],
    [1,1,0,-3,0,6],
    [2,-1,0,1,-1,5],
    [-1,2,0,-2,-1,-1]
]
def gauss(matrix):
    n = len(matrix)
    col_lead = 0
    for i in range(n-1):
        sortZero(matrix,col_lead,i)

        while matrix[i][col_lead] == 0.0 and col_lead<len(matrix[0])-2:
            col_lead+=1
            sortZero(matrix,col_lead,i)

        if matrix[i][col_lead] == 0.0 :
            break

        else:
            factor = matrix[i][col_lead]
            for j in range(len(matrix[0])):
                matrix[i][j]*=1/factor

        for row in range(i+1, n):
            ratio = matrix[row][col_lead]/matrix[i][col_lead]
            for col in range(len(matrix[0])):
                matrix[row][col] = matrix[row][col] - ratio * matrix[i][col]
        col_lead +=1
    return matrix

def jordan(matrix):
    n = len(matrix)
    for i in range (len(matrix)-1,0,-1):
        #finding lead_col
        col_lead = 0 
        while matrix[i][col_lead] ==0 and col_lead<len(matrix[0])-2:
            col_lead +=1
        if matrix[i][col_lead] == 0: 
            continue
        for row in range(i):
            ratio = matrix[row][col_lead]/matrix[i][col_lead]
            for col in range(len(matrix[0])):
                matrix[row][col] = matrix[row][col] - ratio * matrix[i][col]
    return matrix
        

def display(matrix):
    for j in matrix:
        print("[",end='')
        for i in j:
            if i>=0:
                print(f" {round(float(i),2)}",end='')
            else:
                print(round(float(i),2),end='')
            print(" ",end='')
        print("]")
    print()

m = [
    [2,0,8,0,8],
    [0,1,0,4,6],
    [-4,0,6,0,6],
    [0,-2,0,3,-1],
    [2,0,-4,0,-4],
    [0,1,0,-2,0]
]
# display(jordan(gauss(matrix1)))
# display(jordan(gauss(matrix2)))
def isIdentity(matrix):
    if len(matrix)!=len(matrix[0]):
        return False
    for i in range(len(matrix)):
        for j in range(len(matrix[0])):
            if i == j and matrix[i][j] != 1:
                return False
            if i != j and matrix[i][j] != 0:
                return False
    return True


def matrixToSLE(matrix):
    for row in range(len(matrix)):
        eq = ""
        col_lead = 0 
        while matrix[row][col_lead] == 0: 
            if col_lead >= len(matrix[0])-2:
                break
            col_lead += 1

        if matrix[row][col_lead] == 0.0 and matrix[row][col_lead+1]==0:
            continue
        else:
            for col in range (col_lead,len(matrix[0])):
                if matrix[row][col] == 0.0:
                    if eq == "" and col == len(matrix[0])-2:
                        eq += "0"
                    elif col == len(matrix[0])-1:
                        eq += f" = 0.0"
                    else:
                        continue
                elif col == len(matrix[0])-1:
                    eq += f" = {matrix[row][col]}"
                elif eq == "":
                    eq += (f"({matrix[row][col]})X{col+1}")
                else:
                    eq += (f" + ({matrix[row][col]})X{col+1}")
        print(eq)  

char = ['s','t','u','v','w','x','y','z']

def searchInCol(matrix,col,val):

    for i in range (len(matrix)):
        if matrix[i][col] == val:
            return (i)
    return (-1)

def solve(matrix):
    # bisa ada case kalo 0 semua dibawah
    idx = 0
    parameter = []
    # if not isIdentity(elmCol(matrix,len(matrix[0])-1)):
    if True:
        for row in range(len(matrix)):
            col_lead = 0 
            while matrix[row][col_lead] == 0: 
                if col_lead >= len(matrix[0])-2:
                    break
                col_lead += 1
            
            if matrix[row][col_lead] == 0 and matrix[row][col_lead+1] == 0:
                # eq 0 = 0
                continue
            elif matrix[row][col_lead] == 0 and matrix[row][col_lead] != 0:
                # eq 0 = x, x =/= 0 (unsolveable)
                print("unsolvable")

            eq = ""
            # assign parameter ke selain leading one
            for col in range (col_lead+1,len(matrix[0])-1):
                if matrix[row][col]==0 :
                    continue
                elif searchInCol(parameter,0,col+1) == -1 :
                    # index belum di assign ke parameter
                    print(f"X{col+1} = {char[idx]}")
                    parameter += [[col+1,char[idx]]]
                    idx +=1
                # index pasti sudah di assign ke suatu parameter
                if eq == "":
                    eq += f"({-matrix[row][col]}){parameter[searchInCol(parameter,0,col+1)][1]} "
                else:
                    eq += f"+ ({-matrix[row][col]}){parameter[searchInCol(parameter,0,col+1)][1]} "
            if eq == "":
                eq = f"X{col_lead+1} = " + eq + f"({matrix[row][len(matrix[0])-1]})"
            else:
                if matrix[row][len(matrix[0])-1] == 0: 
                    eq = f"X{col_lead+1} = " + eq
                else:
                    eq = f"X{col_lead+1} = " + eq + f"+ ({matrix[row][len(matrix[0])-1]})"
            print(eq)
        print(parameter) 
        
                
                
                


matrix1 = [
    [1,1,-1,-1,1],
    [2,5,-7,-5,-2],
    [2,-1,1,3,4],
    [5,2,-4,2,6]
]
matrix2 =[ 
    [1,-1,0,0,1,3],
    [1,1,0,-3,0,6],
    [2,-1,0,1,-1,5],
    [-1,2,0,-2,-1,-1]
]

matrix3 = [
    [1,0,1,0,1,1],
    [0,1,0,1,0,0],
    [1,1,0,0,0,1]
]
display(jordan(gauss(matrix3)))
solve(jordan(gauss(matrix3)))
# display(jordan(gauss(matrix1)))
# matrixToSLE(jordan(gauss(matrix1)))
                
