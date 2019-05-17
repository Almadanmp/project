package pt.ipp.isep.dei.project.dto;

public class AddressAndLocalDTO {
    private AddressDTO addressDTO;
    private LocalDTO localDTO;

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    public LocalDTO getLocalDTO() {
        return localDTO;
    }

    public void setLocalDTO(LocalDTO localDTO) {
        this.localDTO = localDTO;
    }
}
