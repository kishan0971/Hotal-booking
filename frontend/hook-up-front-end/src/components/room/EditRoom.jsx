
import React, { useEffect, useState } from 'react';
import { getRoomById, updateRoom } from '../utils/ApiFunction';
import { useParams } from 'react-router-dom';
import RoomTypeSelector from '../common/RoomTypeSelector';
import { Card } from 'react-bootstrap';

const EditRoom = () => {
    const [newRoom, setNewRoom] = useState({
        photo: null,
        roomType: "",
        roomPrice: "",
    });

    const [imagePreview, setImagePreview] = useState("");
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const { roomId } = useParams();

    const handleImageChange = (e) => {
        const selectedImage = e.target.files[0];
        setNewRoom({ ...newRoom, photo: selectedImage });
        setImagePreview(URL.createObjectURL(selectedImage));
    }

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setNewRoom({ ...newRoom, [name]: value });
    }

    useEffect(() => {
        const fetchRoom = async () => {
            try {
                const roomData = await getRoomById(roomId);
                setNewRoom(roomData);
                // setImagePreview(roomData.photo);
                setImagePreview(`data:image/png;base64,${roomData.photo}`);

            } catch (error) {
                console.error(error);
            }
        }
        fetchRoom();
    }, [roomId]);

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await updateRoom(roomId, newRoom);
            if (response.status === 200) {
                setSuccessMessage("Room updated successfully!");
                const updatedRoomData = await getRoomById(roomId);
                setNewRoom(updatedRoomData);
                setImagePreview(updatedRoomData.photo);
                setErrorMessage("");
                
            } else {
                setErrorMessage("Error updating room");
                
            }
        } catch (error) {
            console.error(error);
            setErrorMessage(error.message);
        }
    }

    return (
        <>
            <section className="container, mt-5 mb-5">
                <div className="row justify-content-center">
                    <div className="col-md-8 col-lg-6">
                        <h2 className="mt-5 mb-2">Update the Room</h2>
                        <form onSubmit={handleSubmit}>
                            <div className="mb-3">
                                <label htmlFor="roomType" className="form-label">Room Type</label>
                                <div>
                                    <RoomTypeSelector
                                        handleRoomInputChange={handleInputChange} 
                                        newRoom={newRoom}
                                    />
                                </div>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="roomPrice" className="form-label">Room Price</label>
                                <input
                                    className="form-control"
                                    required
                                    id="roomPrice"
                                    name="roomPrice"
                                    type="number"
                                    value={newRoom.roomPrice}
                                    onChange={handleInputChange}
                                />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="photo" className="form-label">Room Photo</label>
                                <input
                                    id="photo"
                                    name="photo"
                                    type="file"
                                    className="form-control"
                                    onChange={handleImageChange}
                                />
                                {imagePreview && (
                                    <img src={imagePreview} alt="Preview Room Photo"
                                        style={{ maxWidth: "400px", maxHeight: "400px" }}
                                        className="mb-3"
                                    />
                                )}
                            </div>
                            <div className="d-grid d-md-flex mt-2">
                                <button type="submit" className="btn btn-outline-primary ml-5">Update Room</button>
                            </div>
                        </form>
                    </div>
                </div>
            </section>
        </>
    );
}

export default EditRoom;
