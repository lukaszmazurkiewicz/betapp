package com.kodilla.betapp.user;

import com.kodilla.betapp.wallet.Wallet;
import com.kodilla.betapp.wallet.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserMapper {
    private final WalletService walletService;

    User mapToUser(final UserDto userDto) {
        Wallet wallet = walletService.getWalletById(userDto.getWalletId());
        return new User(
                userDto.getId(),
                userDto.getLogin(),
                userDto.getPassword(),
                wallet
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getWallet().getId(),
                user.getLogin(),
                user.getPassword());
    }

    public List<UserDto> mapToUserDtoList(final List<User> users) {
        return users.stream()
                .map(u -> mapToUserDto(u))
                .collect(Collectors.toList());
    }
}
