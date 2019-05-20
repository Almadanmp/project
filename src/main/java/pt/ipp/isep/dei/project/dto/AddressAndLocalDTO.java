package pt.ipp.isep.dei.project.dto;

public class AddressAndLocalDTO {
    private AddressDTO addressDTO;
    private LocalDTO localDTO;

    public AddressDTO getAddress() {
        return addressDTO;
    }

    public void setAddress(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    public LocalDTO getLocal() {
        return localDTO;
    }

    public void setLocal(LocalDTO localDTO) {
        this.localDTO = localDTO;
    }
}
