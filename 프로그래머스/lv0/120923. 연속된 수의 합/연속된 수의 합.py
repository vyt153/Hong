def solution(num, total):
    answer = []
    if total == 0:
        for i in range(-(num//2), num//2+1):
            answer.append(i)
    elif num == 1:
        answer.append(total)
    else:
        for i in range(-total+1, total+1):
            if len(answer) == 1:
                print(1)
                break
            result = 0
            j = i
            while result < total:
                result += j
                j += 1
                if result == total and j-i == num:
                    answer.append(i)
        for c in range(i, j):
            answer.append(c)
    return answer