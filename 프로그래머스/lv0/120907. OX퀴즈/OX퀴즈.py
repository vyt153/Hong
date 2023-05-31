def solution(quiz):
    answer = []
    for i in range(0, len(quiz)):
        num = quiz[i].split()
        if num[1] == '+':
            if int(num[0]) + int(num[2]) == int(num[4]):
                answer.append('O')
            else:
                answer.append('X')
        elif num[1] == '-':
            if int(num[0]) - int(num[2]) == int(num[4]):
                answer.append('O')
            else:
                answer.append('X')
    return answer