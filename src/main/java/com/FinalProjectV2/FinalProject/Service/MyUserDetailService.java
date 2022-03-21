//package com.FinalProjectV2.FinalProject.Service;
//
//import com.FinalProjectV2.FinalProject.Repository.UserRepository;
//import com.FinalProjectV2.FinalProject.entity.Role;
//import com.FinalProjectV2.FinalProject.entity.User;
//import com.FinalProjectV2.FinalProject.pojo.MyUserDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;


//class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    UserRepository userRepository;

//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Optional<User> user = Optional.ofNullable(userRepository.findByUserName(userName));
//
//        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
//
//        return user.map(MyUserDetails::new).get();
//    }
//}
