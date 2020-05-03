package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.beans.UserBean;
import com.example.demo.model.UserModel;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserService;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<UserBean> getAllStudent() {
		List<UserModel> userModelList = (List<UserModel>) userRepository.findAll();
		List<UserBean> userBeanList = new ArrayList<>();
		UserBean userBean;

		for (UserModel userModel : userModelList) {
			userBean = new UserBean();

			BeanUtils.copyProperties(userModel, userBean);
			userBean.setPassword("Contact Admin to get It");

			userBeanList.add(userBean);

		}

		return userBeanList;
	}

	/*
	 * //getting a specific record public Student getStudentById(int id) { return
	 * studentRepository.findById(id).get(); }
	 */
	@Override
	public void saveOrUpdate(UserBean userBean) {

		UserModel userModel = new UserModel();
		userBean.setPassword(bCryptPasswordEncoder.encode(userBean.getPassword()));

		BeanUtils.copyProperties(userBean, userModel);

		userRepository.save(userModel);
	}

	/*
	 * //deleting a specific record public void delete(int id) {
	 * studentRepository.deleteById(id); }
	 */

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserModel userModel = userRepository.findByEmail(email);

		if (userModel == null) {
			throw new UsernameNotFoundException(email);
		}

		return new User(userModel.getEmail(), userModel.getPassword(), new ArrayList<>());
	}

	@Override
	public UserBean getUserByUsername(String username) {

		UserModel userModel = userRepository.findByEmail(username);
		UserBean userBean = new UserBean();

		BeanUtils.copyProperties(userModel, userBean);

		return userBean;
	}

	@Override
	public UserBean getUserById(Long userId) {

		Optional<UserModel> userModel = userRepository.findById(userId);
		UserBean userBean = new UserBean();

		BeanUtils.copyProperties(userModel.get(), userBean);

		return userBean;
	}
}