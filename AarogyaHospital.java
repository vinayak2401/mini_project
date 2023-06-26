import java.util.*;

class NullPointerException extends Exception {
  NullPointerException(String str) {
    super(str);
  }
}

// the main class
class AarogyaHospital {
  Scanner scanner = new Scanner(System.in);
  ArrayList<AarogyaMember> members = new ArrayList<>();

  public void addMember() {
    AarogyaMember newMember = new AarogyaMember();
    newMember.input();
    members.add(newMember);
  }

  public void displayMembers() {
    for (AarogyaMember member : members) {
      System.out.println("Patient ID: " + member.id);
      System.out.println("Patient Name: " + member.name);
      System.out.println("Patient Age: " + member.age);
      System.out.println("Patient Gender: " + member.gender);
      System.out.println("Patient Aadhar Card Number: " + member.aadharCardNumber);
      System.out.println("Patient Contact Number: " + member.contactNumber);
      System.out.println("Patient City: " + member.city);
      System.out.println("Patient Address: " + member.address);
      System.out.println("Patient Date of Admission: " + member.dateOfAdmission);
      System.out.println("Patient Guardian Name: " + member.guardianName);
      System.out.println("Patient Guardian Address: " + member.guardianAddress);
      System.out.println("Patient Guardian Contact Number: " + member.guardianContactNumber);
      System.out.println();
    }
  }

  public void searchMemberById(int id) {
    for (AarogyaMember member : members) {
      if (member.getMemberId() == id) {
        System.out.println(member.getName() + " has the ID " + id);
        return; // Exit the method once a matching member is found
      }
    }
    // If no member with the specified ID is found, print an error message
    System.out.println("Member with ID " + id + " was not found.");
  }

  public void searchMemberFromCity(String city) throws NullPointerException {
    boolean found = false;
    for (AarogyaMember member : members) {
      if (member.getCity().equalsIgnoreCase(city)) {
        System.out.println(member.getName() + " is from " + city);
        found = true;
      }
    }
    if (!found) {
      throw new NullPointerException("No member with city " + city + " found.");
    }
  }

  public void searchMemberFromParticularAgeGroup() {
    Scanner sc = new Scanner(System.in);
    int count = 0;
    System.out.println("Enter Minimum age :");
    int minAge = sc.nextInt();
    System.out.println("Enter Maximum age :");
    int maxAge = sc.nextInt();

    for (AarogyaMember member : members) {
      if (member.age >= minAge && member.age <= maxAge) {
        System.out.print(member.getName() + " ");
        count++;
      }
    }
    if (count > 0) {
      System.out.print("is of the required age group \n");
    } else {
      System.out.println("The age group your searching for is not present");
    }
  }

  public void recoveredInformation() {
    Scanner s = new Scanner(System.in);
    System.out.print("Enter patient ID: ");
    int id = s.nextInt();
    AarogyaMember mem = null;
    for (AarogyaMember member : members) {
      if (member.getId() == id) {
        mem = member;
        break;
      }
    }

    if (mem != null) {
      System.out.print("Has the patient recovered? (yes/no): ");
      String recoveredStr = s.next();
      boolean recovered = recoveredStr.equalsIgnoreCase("yes");
      mem.setRecovered(recovered);
      System.out.println("Recovered information updated for patient with ID " + mem.getId());
    } else {
      System.out.println("No patient found with ID " + id);
    }
  }

  public void deleteInformation(int patientId) throws NullPointerException {
    for (AarogyaMember member : members) {
      if (member.getMemberId() == patientId) {
        members.remove(member);
        System.out.println("Patient with ID " + patientId + " has been removed.");
        return;
      }
    }
    throw new NullPointerException("Patient with ID " + patientId + " not found.");
  }

  // class for storing the patient information
  class AarogyaMember {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String aadharCardNumber;
    private String contactNumber;
    private String city;
    private String address;
    private String dateOfAdmission;
    private String guardianName;
    private String guardianAddress;
    private String guardianContactNumber;
    private boolean recovered;

    public int getMemberId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public int getId() {
      return id;
    }

    public String getCity() {
      return city;
    }

    public boolean isRecovered() {
      return recovered;
    }

    public void setRecovered(boolean recovered) {
      this.recovered = recovered;
    }

    // for taking patient information
    public void input() {
      // To make sure patiieint's have unique id
      boolean isUnique = false;
      while (!isUnique) {
        System.out.println("Enter patient's Id: ");
        this.id = scanner.nextInt();
        isUnique = true;
        for (AarogyaMember member : members) {
          if (member.id == this.id) {
            System.out.println("Patient ID already exists. Please enter a unique ID.");
            isUnique = false;
            break;
          }
        }
      }

      System.out.println("Enter patient's name: ");
      scanner.nextLine();
      this.name = scanner.nextLine();

      while (true) {
        System.out.println("Enter patient's age: ");
        this.age = scanner.nextInt();
        scanner.nextLine();
        if (age > 0 && age < 100) {
          break;
        } else {
          System.out.println("Invalid Age entered, Should contain 2 digits. Please try again.");
        }
      }

      System.out.println("Enter patient's gender: ");
      this.gender = scanner.nextLine();

      while (true) {
        System.out.println("Enter patient's Aadhar Card number: ");
        this.aadharCardNumber = scanner.nextLine();
        if (aadharCardNumber.length() == 12 && aadharCardNumber.matches("[0-9]+")) {
          break;
        } else {
          System.out.println("Invalid Aadhar Card number entered, Should contain 12 digits. Please try again.");
        }
      }

      while (true) {
        System.out.println("Enter patient's contact number: ");
        this.contactNumber = scanner.nextLine();
        if (contactNumber.length() == 10 && contactNumber.matches("[0-9]+")) {
          break;
        } else {
          System.out.println("Invalid Contact number entered, Should contain 10 digits. Please try again.");
        }
      }

      System.out.println("Enter patient's city: ");
      this.city = scanner.nextLine();

      System.out.println("Enter patient's address: ");
      this.address = scanner.nextLine();

      System.out.println("Enter patient's date of admission: ");
      this.dateOfAdmission = scanner.nextLine();

      System.out.println("Enter patient's guardian name: ");
      this.guardianName = scanner.nextLine();

      System.out.println("Enter patient's guardian address: ");
      this.guardianAddress = scanner.nextLine();

      while (true) {
        System.out.println("Enter patient's guardian contact number: ");
        this.guardianContactNumber = scanner.nextLine();
        if (guardianContactNumber.length() == 10 && guardianContactNumber.matches("[0-9]+")) {
          break;
        } else {
          System.out.println("Invalid Contact number entered, Should contain 10 digits. Please try again.");
        }
      }
    }
  }

  // pick the choice of task to be performed
  static long choices() {

    System.out.println("Press 1 for adding new member");
    System.out.println("Press 2 to view list of all available members");
    System.out.println("Press 3 to search member by ID");
    System.out.println("Press 4 to search member from a particular city");
    System.out.println("Press 5 to search member from a particular age group");
    System.out.println("Press 6 to mark recovery of a member");
    System.out.println("Press 7 to delete data of a member");
    System.out.println("Press 0 to exit");

    Scanner ip = new Scanner(System.in); // create scanner class to take input
    long option = ip.nextLong();
    return option;
  }

  public static void main(String args[]) {

    AarogyaHospital hospital = new AarogyaHospital();

    while (true) {
      // take choice
      long option = choices();

      // invalid choice
      if (option < 0 || option > 7) {
        System.out.println("Invalid choice");
      }
      // take the input and add in the arrayList
      else if (option == 1) {
        hospital.addMember();
      }
      // iterate and print all the patients information
      else if (option == 2) {
        hospital.displayMembers();
      }
      // print details of the patient with a particular id (take id as input)
      else if (option == 3) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the Id to search:");
        hospital.searchMemberById(s.nextInt());
      }
      // to print all the patients from a particular city (take city as input)
      else if (option == 4) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the city name to search:");
        try {
          hospital.searchMemberFromCity(s.next());
        } catch (NullPointerException e) {
          System.out.println(e);
        }
      }
      // to print details of all the patients in a particular age group (take age
      // limits as input)
      else if (option == 5) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter to check for particular age group :");
        hospital.searchMemberFromParticularAgeGroup();
      }
      // take member id as input to maintain recovered information of patient
      else if (option == 6) {
        hospital.recoveredInformation();
      }
      // take member id as input to delete patient information
      else if (option == 7) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Id to delete information:");
        try {
          hospital.deleteInformation(s.nextInt());
        } catch (NullPointerException e) {
          System.out.println(e);
        }
      } else {
        System.out.println("Thank you!!!!");
        break;
      }
    }

  }
}