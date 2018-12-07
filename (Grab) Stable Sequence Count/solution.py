a = [1, 2, 3, 4, 6, 3, 4, 5, 10, 13, 14, 15, 16, 17]

out = 0
prev_vel = a[1] - a[0]
leng_of_current_group = 2
for i in range(2, len(a)):
    curr_vel = a[i] - a[i - 1]
    if curr_vel == prev_vel:
        leng_of_current_group += 1
    elif leng_of_current_group >= 3:
        out += ((leng_of_current_group + 1) *
                (leng_of_current_group - 4) / 2 + 3)
        leng_of_current_group = 2

    prev_vel = curr_vel

if leng_of_current_group >= 3:
    out += ((leng_of_current_group + 1) * (leng_of_current_group - 4) / 2 + 3)

print(int(out))
