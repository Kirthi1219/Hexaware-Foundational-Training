package service;
import entity.Admin;
import exception.AdminNotFoundException;
import exception.DatabaseConnectionException;
import exception.InvalidInputException;
import dao.AdminDAO;

public class AdminService implements IAdminService{
	private AdminDAO adminDAO = new AdminDAO();
	
	@Override
	public Admin getAdminById(int adminId) {
        try {
        	if (adminId <= 0) {
                throw new InvalidInputException("Invalid admin ID: " + adminId);
            }
            Admin admin = adminDAO.getAdminById(adminId);
            if (admin != null) {
                System.out.println("Admin found: " + admin);
            } else {
            	throw new AdminNotFoundException("No admin found with ID: " + adminId);
            }
            return admin;
        } catch (DatabaseConnectionException e) {
            System.err.println("Error retrieving admin by ID: " + e.getMessage());
            return null;
        } catch (AdminNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidInputException e) {
			e.printStackTrace();
			return null;
		}
    }
	
	@Override
	public Admin getAdminByUsername(String username) {
        try {
        	 if (username == null || username.trim().isEmpty()) {
                 throw new InvalidInputException("Invalid admin username: " + username);
             }
            Admin admin = adminDAO.getAdminByUsername(username);
            if (admin != null) {
                System.out.println("Admin found: " + admin);
            } else {
            	throw new AdminNotFoundException("No admin found with username: " + username);
            }
            return admin;
        } catch (DatabaseConnectionException e) {
            System.err.println("Error retrieving admin by username: " + e.getMessage());
            return null;
        } catch (AdminNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidInputException e) {
			e.printStackTrace();
			return null;
		}
    }
	
	 @Override
	 public boolean registerAdmin(Admin admin) {
	        try {
	        	if (admin == null || admin.getUsername() == null || admin.getUsername().trim().isEmpty()) {
	                throw new InvalidInputException("Invalid admin data provided for registration.");
	            }
	            boolean success = adminDAO.registerAdmin(admin);
	            if (success) {
	                System.out.println("Admin registered successfully! Username: " + admin.getUsername());
	            } else {
	                System.out.println("Failed to register admin.");
	            }
	            return success;
	        } catch (DatabaseConnectionException e) {
	            System.err.println("Error registering admin: " + e.getMessage());
	            return false;
	        } catch (InvalidInputException e) {
				e.printStackTrace();
				return false;
			}
	    }
	 
	 @Override
	 public boolean updateAdmin(Admin admin) {
	        try {
	        	if (admin == null || admin.getUsername() == null || admin.getUsername().trim().isEmpty()) {
	                throw new InvalidInputException("Invalid admin data provided for update.");
	            }
	            boolean success = adminDAO.updateAdmin(admin);
	            if (success) {
	                System.out.println("Admin updated successfully: " + admin.getUsername());
	            } else {
	            	throw new AdminNotFoundException("Failed to update admin. Admin not found.");
	            }
	            return success;
	        } catch (DatabaseConnectionException e) {
	            System.err.println("Error updating admin: " + e.getMessage());
	            return false;
	        } catch (AdminNotFoundException e) {
				e.printStackTrace();
				return false;
			} catch (InvalidInputException e) {
				e.printStackTrace();
				return false;
			}
	    }
	 
	 @Override
	 public boolean deleteAdmin(int adminId) {
		 	
	        try {
	        	if (adminId <= 0) {
		            throw new InvalidInputException("Invalid admin ID for deletion: " + adminId);
		        }
	            boolean success = adminDAO.deleteAdmin(adminId);
	            if (success) {
	                System.out.println("Admin deleted successfully.ID: " + adminId);
	            } else {
	            	throw new AdminNotFoundException("Failed to delete admin. Admin with ID " + adminId + " not found.");
	            }
	            return success;
	        } catch (DatabaseConnectionException e) {
	            System.err.println("Error deleting admin: " + e.getMessage());
	            return false;
	        } catch (AdminNotFoundException e) {
				e.printStackTrace();
				return false;
			} catch (InvalidInputException e) {
				e.printStackTrace();
				return false;
			}
	    }
}
